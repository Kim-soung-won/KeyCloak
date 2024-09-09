import React from "react";
// import { useCounterStore } from "../../Stores/ZustandHooks";
import TestComp2 from "./TestComp2";

const TestComp1 = () => {

    // const { count, decrement } = useCounterStore();

    return (
        <div>
            <div>
                <div className="text-5xl">하이라이스</div>
                {/* <div className="text-5xl">{count}</div>
                <button onClick={decrement}>감소</button> */}
                <TestComp2 />
            </div>
        </div>
    )
}

export default TestComp1;