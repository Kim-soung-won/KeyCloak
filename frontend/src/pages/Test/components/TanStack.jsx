import React, { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import { url } from "../../../utils/single";
import Mutation from "./Mutation";


const TanStack = () => {

    const [apiData, setApiData] = useState("")

    const info = useQuery({
        queryKey: ["info"], queryFn: async () => {
            const res = await axios.get(`${url}/test`);
            setApiData(res.data);
            return res.data;
        }
        ,staleTime: 30000 // 데이터가 신선한 상태로 취급되는 시간
        // ,refetchInterval: 10000 // 자동 불러오기 시간
        ,cacheTime: 10000  // 데이터가 cache에 저장되는 시간
        ,enabled: true // 쿼리를 활성화할지 여부
        ,retry:5 // 응답이 제대로 전해지지 않을 때 몇번 재요청을 보낼지 (default : 3)
        ,retryDelay : 5000 // 재요청 간격을 지정
        ,refetchOnWindowFocus : true // 브라우저 창이 포커스받을 때 자동으로 쿼리를 재요청할지 (default : true)
        ,refetchOnReconnect : true // 인터넷 연결이 복구됐을 때, 쿼리를 재요청할지 (default : true)
    });

    


    return (
        <div>
            <div>
                {info.isLoading ? (
                    <div>로딩중...</div>
                ) : info.isError ? (
                    <div>{info.error.message}</div>
                ) : (
                    <div>{apiData}</div>
                )}
            </div>
            <button className="px-4 py-2 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition duration-200" onClick={info.refetch}>다시 불러오기</button>
            <Mutation/>
        </div>
        
    )
}

export default TanStack;