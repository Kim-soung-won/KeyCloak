import React from "react";
import TestComp1 from "./components/TestComp1";
// import TestComp2 from "./components/TestComp2";

const TestPage = () => {

    return (
        <div className="flex items-center justify-center h-screen max-w-2xl mx-auto p-4 min-w-full bg-white border border-blue-200 rounded-lg shadow-md">
            <div>
                <TestComp1 />
            </div>
        </div>
    )
}

export default TestPage;