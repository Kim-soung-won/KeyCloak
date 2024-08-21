import { lazy, Suspense } from "react";
import { createBrowserRouter } from "react-router-dom";



const LoginComp = lazy(() => import("../pages/Login/components/LoginComp"));

const root = createBrowserRouter([
    {
        path:"/login",
        element: <Suspense><LoginComp/></Suspense>
    }
])

export default root;