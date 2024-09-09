import React, {useState, useEffect} from "react";
import { useNavigate, useParams } from "react-router-dom";
import jwtAxios from "../../../../utils/jwtAxios";

const ClientDetailViewComp = () => {

    const navigate = useNavigate();
    const {id} = useParams();

    const [result, setResult] = useState({});

    const getDetailClient = async() => {
        const response = await jwtAxios.get("/we-auth/api/client/detail/"+id)
        .then((res) => {
            console.log("res data :: ",res.data);
            setResult(res.data);
            console.log("result :: " + result);
        }).catch((error) => {
            console.log("33333 :: ",error);
        });
    }

    useEffect(() => {
        getDetailClient();
    }, []);

    return(
        <div>
            <button className="px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-200"
            onClick={() => navigate("/client/list")}
            >리스트로 돌아가기</button>
            <h1>UUID : {result.id}</h1>
            <h1>ClientID : {result.clientId}</h1>
            <h1>NAME : {result.name}</h1>
            <h1>Description : {result.description}</h1>
            <h1>rootUrl : {result.rootUrl}</h1>
            <h1>baseUrl : {result.baseUrl}</h1>
            <h1>clientAuthenticatorType : {result.clientAuthenticatorType}</h1>
        </div>
    )

}

export default ClientDetailViewComp;