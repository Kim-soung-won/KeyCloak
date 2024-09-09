import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import jwtAxios from "../../../../utils/jwtAxios";

const ClientInputComp = () => {

    const navigate = useNavigate();

    const [id , setId] = useState("");
    const [clientId, setClientId] = useState("");
    const [clientName, setClientName] = useState("");
    const [clientDescription, setClientDescription] = useState("");

    const onIdChange = (e) => {
        setId(e.target.value);
    }
    const onClientIdChange = (e) => {
        setClientId(e.target.value);
    }
    const onClientNameChange = (e) => {
        setClientName(e.target.value);
    }
    const onClientDescriptionChange = (e) => {
        setClientDescription(e.target.value);
    }

    const doCreateClient = async() => {
        try{
            const response = await jwtAxios.post("/we-auth/api/create/client", {
                id: id,
                clientId: clientId,
                name: clientName,
                description: clientDescription
            }).then((res) => {
                alert( id +"클라이언트가 생성되었습니다.")
                navigate("/client/list");
            }).catch((error) => {
                console.log("33333 :: ",error);
            });
        } catch (error) {
            console.log("44444 :: ",error);
        }
    }


    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <div className="bg-white p-8 rounded-lg shadow-md w-96">
                <h2 className="text-2xl font-bold mb-6 text-center">회원가입</h2>
                <div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="id">
                            UUID [ API 사용시 식별자, 미입력 시 UUID 자동 생성, DB에 기록 안됨 ]
                        </label>
                        <input
                            type="text"
                            id="id"
                            placeholder="UUID를 입력하세요"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            onChange={onIdChange}
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="clientId">
                            clientId 
                        </label>
                        <input
                            type="text"
                            id="clientId"
                            placeholder="clientId를 입력하세요"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            onChange={onClientIdChange}
                        />
                    </div>
                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
                            client name
                        </label>
                        <input
                            type="text"
                            id="name"
                            placeholder="name을 입력하세요"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            onChange={onClientNameChange}
                        />
                    </div>

                    <div className="mb-6">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="description">
                            client description 
                        </label>
                        <input
                            type="text"
                            id="description"
                            placeholder="description을 입력하세요"
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            onChange={onClientDescriptionChange}
                        />
                    </div>
                    
                    
                    <div className="flex items-center justify-between">
                        <button
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                            onClick={doCreateClient}
                        >
                            가입하기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );

}

export default ClientInputComp;