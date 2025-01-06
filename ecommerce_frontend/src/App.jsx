import Header from "./components/Header.jsx";
import { Outlet } from "react-router";


function App() {

    return (
        <div className="flex flex-col gap-5">
            <header> <Header /> </header>
            <main>
                <Outlet />
            </main>
        </div>
    )
}

export default App
