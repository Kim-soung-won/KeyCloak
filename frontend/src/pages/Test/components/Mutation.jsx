import React, { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { url } from "../../../utils/single";
import axios from "axios";

const Mutation = () => {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [pwd, setPwd] = useState("");

    const onInputEmail = (e) => {
        setEmail(e.target.value);
        console.log(email);
    };
    const onInputPassword = (e) => {
        setPwd(e.target.value);
        console.log(pwd);
    };
    const onInputNickname = (e) => {
        setName(e.target.value);
        console.log(name);
    };

    
    const transaction = useMutation({ mutationKey : ["post"], mutationFn: async () => {
            const data = {
                memberName : name,
                memberEmail : email,
                memberPwd : pwd
            }
            const res = await axios.post(`${url}/test`, data);
            console.log(res);
        },onSuccess : () => {
            console.log("SUCCESS");
        },onError : (error) => {
            console.log("ERROR" , error);
        }
    });

    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <div>
                {transaction.isIdle ? (
                    <div>로딩중...</div>
                ) : transaction.isError ? (
                    <div>{transaction.error.message}</div>
                ) : (
                    <div>{name}</div>
                )}
            </div>
            <div className="bg-white p-8 rounded-lg shadow-md w-96">
                <h2 className="text-2xl font-bold mb-6 text-center">Mutation</h2>
                <div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
                            이메일
                        </label>
                        <input
                            type="text"
                            id="email"
                            placeholder="사용자 이름을 입력하세요"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            onChange={onInputEmail}
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password">
                            비밀번호
                        </label>
                        <input
                            type="password"
                            id="password"
                            placeholder="비밀번호를 입력하세요"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            onChange={onInputPassword}
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
                            닉네임
                        </label>
                        <input
                            type="text"
                            id="username"
                            placeholder="사용자 이름을 입력하세요"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            onChange={onInputNickname}
                        />
                    </div>
                    <div className="flex items-center justify-between">
                        <button
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            onClick={()=>transaction.mutate()}
                        >
                            가입하기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Mutation