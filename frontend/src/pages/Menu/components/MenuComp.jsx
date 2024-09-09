import React, { useState } from "react";
import { useNavigate } from "react-router-dom";


const MenuComp = () => {

    const navigate = useNavigate();

    // 요청에 대한 응답을 화면에 표시하기 위한 State
    const [viewResponse, setResponse] = useState("대기중");

    const setViewResponse = (response) => {
        setResponse(response);
    }

    const goClient = () => {
        navigate("/client");
    }
    const goClientListView = () => {
        navigate("/client/list");
    }
    
    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <div>
            <h1 className="text-3xl font-bold text-gray-800 mb-4">Menu</h1>
    
            <button 
                onClick={goClient} 
                className="px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-200"
            >
                Client  생성하기
            </button>
            <br></br>
            <br></br>
            <button 
                onClick={goClientListView} 
                className="px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-200"
            >
                내 Realm 속 Client  목록 보기 [ FROM KeyCloak ]
            </button>
            <br></br>
            <br></br>
            <button 
                onClick={goClientListView} 
                className="px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-200"
            >
                내 Realm 속 Client  목록 보기 [ FROM DB ]
            </button>
            <div className="text-xl text-gray-600 mt-4">{viewResponse}</div>
            </div>
        </div>
    );
}

export default MenuComp;