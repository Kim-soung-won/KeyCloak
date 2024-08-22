import { lazy, Suspense } from "react";
import { createBrowserRouter } from "react-router-dom";



const LoginComp = lazy(() => import("../pages/Login/components/LoginComp"));
const SignUpComp = lazy(() => import("../pages/SignUp/components/SignUpComp"));

const root = createBrowserRouter([
    {
        path:"/login",
        element: <Suspense><LoginComp/></Suspense>
    },
    {
        path:"/join",
        element: <Suspense><SignUpComp/></Suspense>
    }
])

export default root;