import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { url, jsonHeader } from "../../../utils/single";


const SignUpComp = () => {

    const navigate = useNavigate();

    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const onInputEmail = (e) => {
        setEmail(e.target.value);
        console.log(email);
    };
    const onInputPassword = (e) => {
        setPassword(e.target.value);
        console.log(password);
    };
    const onInputNickname = (e) => {
        setNickname(e.target.value);
        console.log(nickname);
    };

    const doSignUp = (nickname, email, passwd) => {
        axios.post(`${url}/signup`, {
            memberName : nickname,
            memberEmail: email,
            memberPwd: passwd
        }, {
            headers: jsonHeader
        }).then((res) => {
            console.log(res);
            alert(res.data.message);
            navigate("/login");
        }).catch((err) => {
            console.log(err);
        });
    }

    

    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <div className="bg-white p-8 rounded-lg shadow-md w-96">
                <h2 className="text-2xl font-bold mb-6 text-center">회원가입</h2>
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
                            onClick={()=>doSignUp(nickname, email, password)}
                        >
                            가입하기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SignUpComp;