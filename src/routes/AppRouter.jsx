import { Route, Routes } from "react-router-dom"
import Header from "../../components/app"
import HomePage from "../pages/mainHouse/HomePage"
import RelasesPage from "../pages/mainHouse/RelasesPage"
import BlogPage from "../pages/mainHouse/BlogPage"
import Error from "../routes/Error"
import HomePageView from "../view/Home/HomePageView"

export const AppRouter = () => {
    return <>
        <Routes>
            <Route>
                <Route path="/"  element={<HomePageView/>} errorElement={<Error/>}/>
                    <Route index element={<HomePage/>} errorElement={<Error/>}/>
                    <Route path="/relases" element={<RelasesPage/>} errorElement={<Error/>}/>
                    <Route path="/blog" element={<BlogPage/>} errorElement={<Error/>}/>
                    <Route path="/a" element={<HomePageView/>} errorElement={<Error/>}/>
            </Route>
        </Routes>
    
    </>
}
export default AppRouter;