import React from "react";
import { useColorStore } from "../../Stores/ZustandHooks";
import TestComp4 from "./TestComp4";

const TestComp3 = () => {

    const { color, changeColor } = useColorStore();

    return (
        <div>
            <div>
                <div className="text-5xl">{color}</div>
                <button onClick={changeColor}>변경</button>
                <TestComp4 />
            </div>
        </div>
    )
}

export default TestComp3;