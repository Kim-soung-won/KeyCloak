import { lazy, Suspense } from "react";
import { createBrowserRouter } from "react-router-dom";



const LoginComp = lazy(() => import("../pages/Login/components/LoginComp"));
const SignUpComp = lazy(() => import("../pages/SignUp/components/SignUpComp"));
const Test = lazy(() => import("../pages/test"));
const MenuComp = lazy(() => import("../pages/Menu/components/MenuComp"));
const ClientInputComp = lazy(() => import("../pages/Menu/components/CreateClient/ClientInputComp"));
const ClientListViewComp = lazy(() => import("../pages/Menu/components/ClientList/ClientListViewComp"));
const ClientModiComp = lazy(() => import("../pages/Menu/components/CreateClient/ClientModiComp"));
const ClientDetailViewComp = lazy(() => import("../pages/Menu/components/ClientDetail/ClientDetailViewComp"));
const TestPage = lazy(() => import("../pages/Test/TestPage"));
const TanStack = lazy(() => import("../pages/Test/components/TanStack"));

const root = createBrowserRouter([
    {
        path:"",
        element: <Suspense><TestPage/></Suspense>
    },
    {
        path:"/query",
        element: <Suspense><TanStack/></Suspense>
    },
    {
        path:"/login",
        element: <Suspense><LoginComp/></Suspense>
    },
    {
        path:"/join",
        element: <Suspense><SignUpComp/></Suspense>
    },
    {
        path:"/test",
        element: <Suspense><Test/></Suspense>
    },
    {
        path:"/menu",
        element: <Suspense><MenuComp/></Suspense>
    },
    {
        path:"/client",
        element: <Suspense><ClientInputComp/></Suspense>
    },
    {
        path:"/client/list",
        element: <Suspense><ClientListViewComp/></Suspense>
    },
    {
        path:"/client/update/:id",
        element: <Suspense><ClientModiComp/></Suspense>
    },
    {
        path:"/client/detail/:id",
        element: <Suspense><ClientDetailViewComp/></Suspense>
    }
])

export default root;