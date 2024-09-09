import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import jwtAxios from "../../../../utils/jwtAxios";

const ClientModiComp = () => {

    const {id} = useParams();

    const navigate = useNavigate();
    const [clientName, setClientName] = useState("");
    const [clientDescription, setClientDescription] = useState("");

    const onClientNameChange = (e) => {
        setClientName(e.target.value);
    }
    const onClientDescriptionChange = (e) => {
        setClientDescription(e.target.value);
    }

    const doModifyClient = async() => {
        try{
            const response = await jwtAxios.put("/we-auth/api/update/client/"+id, {
                name: clientName,
                description: clientDescription
            }).then((res) => {
                alert( id +"클라이언트가 수정되었습니다.")
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
                <h2 className="text-2xl font-bold mb-6 text-center">클라이언트 수정</h2>
                <div>
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
                            onClick={doModifyClient}
                        >
                            가입하기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );

}

export default ClientModiComp;