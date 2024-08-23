import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import {jsonHeader, serviceAUrl } from "../../../utils/single";


const MenuComp = () => {

    const navigate = useNavigate();

    const [isLogin, setIsLogin] = useState("대기중");

    const checkLogin = async () => {
        try{
            const response = await axios({
                url: `${serviceAUrl}/menu`,
                method: "GET",
                headers: jsonHeader,
                withCredentials: true
            });
            console.log(response);
            if(response.status === 200){
                setIsLogin("로그인 했어요");
            }
        } catch (error) {
            console.log(error);
            setIsLogin("로그인 아직 안했어요");
        }
    }
    
    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
             <h1 className="text-3xl font-bold text-gray-800 mb-4">Menu</h1>
        <button 
            onClick={checkLogin} 
            className="px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-200"
        >
            로그인 체크
        </button>
        <h1 className="text-xl text-gray-600 mt-4">{isLogin}</h1>
        </div>
    );
}

export default MenuComp;