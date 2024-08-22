import React, { useEffect, useState } from "react";
import axios from "axios";
import { url, jsonHeader } from "../utils/single";

const Test = () => {

    const [test, setTest] = useState("");

    const testRequest = async () => {
        try {
            const response = await axios({
                url: `${url}/test`,
                method: "GET",
                headers: jsonHeader,
                withCredentials: true
            });
            console.log(response);
            if (response.data.code === 200) {
                setTest(response.data.message);
                alert("로그인 성");
            }
        } catch (error) {
            console.log(error);
            alert("로그인 실패");
        }
    }

    useEffect(() => {
        testRequest();
        console.log("Test");
    }, []);
    return (
        <div>
            <h1>{test}</h1>
        </div>
    );
};


export default Test;
