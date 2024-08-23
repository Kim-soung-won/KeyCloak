import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { url, urlNoPort } from "../../../utils/single";

const LoginComp = () => {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const onInputEmail = (e) => {
        setEmail(e.target.value);
        console.log(email);
    }
    const onInputPassword = (e) => {
        setPassword(e.target.value);
        console.log(password);
    }
    
    const doLogin = async (email, passwd) => {
        try {
            const formData = new FormData();
            formData.append("username", email);
            formData.append("password", passwd);
            const response = await axios({
                url: `${url}/login`,
                method: "POST",
                data: formData,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Access-Control-Allow-Origin': `${urlNoPort}:3131`
                },
                withCredentials: true
            });
            console.log(response);
            if (response.status === 200) {
                alert("로그인 성공");
                navigate("/menu");
            }
        } catch (error) {
            console.log(error);
            alert("로그인 실패");
        }
    }

    const letSignUp = () => {
        navigate("/join");
    }

    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <div className="bg-white p-8 rounded-lg shadow-md w-96">
                <h2 className="text-2xl font-bold mb-6 text-center">로그인</h2>
                <div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username">
                            아이디
                        </label>
                        <input
                            type="text"
                            id="username"
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
                    <div className="flex items-center justify-between">
                        <button
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            onClick={() => doLogin(email, password)}
                        >
                            로그인
                        </button>
                    </div>
                </div>
                <p className="mt-4 text-center text-gray-600 text-sm">
                    계정이 없으신가요? <div onClick={letSignUp} className="text-blue-500 hover:text-blue-700">가입하기</div>
                </p>
            </div>
        </div>
    );
}

export default LoginComp;