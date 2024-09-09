import React, { useEffect, useState } from "react";
import { useCounterStore } from "../../Stores/ZustandHooks";
import TestComp3 from "./TestComp3";

const TestComp2 = () => {

    const { count, decrement } = useCounterStore();
    const [num, setNum ] = useState(0);

    useEffect(() => {
        setNum(num + 1);
    }, []);

    return (
        <div>
            <div>
                <div className="text-5xl">{count}</div>
                <div className="text-5xl">{num}</div>
                <button onClick={decrement}>감소</button>
                <TestComp3 />
            </div>
        </div>
    )
}
export default TestComp2;