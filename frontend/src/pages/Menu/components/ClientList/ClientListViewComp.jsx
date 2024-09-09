import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import jwtAxios from "../../../../utils/jwtAxios";


const ClientListViewComp = () => {

    const navigate = useNavigate();

    const [data, setData] = useState([]);

    const getClientList = async() => {
        const response = await jwtAxios.get("/we-auth/api/clients/list")
        .then((res) => {
            setData(res.data);
        }).catch((error) => {
            console.log("33333 :: ",error);
        });
    }

    const deleteClient = async(id) => {
        const response = await jwtAxios.delete("/we-auth/api/delete/client/"+id)
        .then((res) => {
            alert(id +"클라이언트가 삭제되었습니다.")
            getClientList();
        }).catch((error) => {
            console.log("33333 :: ",error);
        });
    }

    const updateClient = (id) => {
        navigate("/client/update/"+id);
    }


    const createClient = () => {
        navigate("/client");
    }

    const detailClient = (id) => {
        navigate("/client/detail/"+id);
    }

    useEffect(() => {
        getClientList();
    }, []);

    return (
        <div className="max-w-2xl mx-auto p-4">
            <h1 className="text-2xl font-bold mb-4">클라이언트 리스트</h1>  
            <button className="px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-200"
            onClick={createClient}
            >클라이언트 생성하기</button>
            <table className="min-w-full bg-white border border-blue-200 rounded-lg shadow-md">
                <thead>
                    <tr className="bg-blue-500 text-gray-600 uppercase text-sm leading-normal">
                        <th className="py-3 px-6 text-left">UUID</th>
                        <th className="py-3 px-6 text-left">Client ID</th>
                        <th className="py-3 px-6 text-left">Name</th>
                        <th className="py-3 px-6 text-left">Description</th>
                        <th className="py-3 px-6 text-center">Actions</th>
                        <th className="py-3 px-6 text-center">Actions</th>
                        <th className="py-3 px-6 text-center">Actions</th>
                    </tr>
                </thead>
                <tbody className="text-gray-600 text-sm font-black">
                    {data.map(item => (
                        <tr key={item.id} className="border-b border-gray-200 hover:bg-gray-100">
                            <td className="py-3 px-6">{item.id}</td>
                            <td className="py-3 px-6">{item.clientId}</td>
                            <td className="py-3 px-6">{item.name}</td>
                            <td className="py-3 px-6">{item.description || '없음'}</td>
                            <td className="py-3 px-6 bg-red-500"
                            onClick={() => deleteClient(item.id)}
                            >삭제하기</td>
                            <td className="py-3 px-6 bg-yellow-500"
                            onClick={() => updateClient(item.id)}
                            >수정하기</td>
                            <td className="py-3 px-6 bg-green-500"
                            onClick={() => detailClient(item.id)}
                            >상세보기</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}


export default ClientListViewComp;