import { useState } from 'react';
import { Search, ShoppingCart, User } from "lucide-react";
import Menu from "./Menu.jsx";
import UserDropdown from "./UserDowpdown.jsx"

function Header(){
    const [search, setSearch] = useState();

    return (
        <nav className="flex items-center justify-around w-screen p-4 text-center text-white bg-black border-b-4 border-dark-purple">
            <a href="https://github.com/nicollasprado" target="blank">
                <img src="https://i.imgur.com/xHxDkxT.png" alt="Logo"></img>
            </a>
            <div className="flex gap-10">
                <div className="flex text-black">
                    <input
                        id="search"
                        type="text"
                        placeholder="Buscar produto"
                        className="p-1 pl-2 w-[35em] h-[2.5em] rounded-l-2xl outline-none"
                        value={search}
                        onChange={(event) => setSearch(event.target.value)}
                    />
                    <button
                        className="px-4 bg-light-purple2 rounded-r-md hover:bg-light-purple">
                        <Search />
                    </button>
                </div>
                <ul className="flex items-center gap-10">
                    <li> <Menu>Computador</Menu> </li>
                    <li> <Menu>Console</Menu> </li>
                    <li> <Menu>Periféricos</Menu> </li>
                </ul>
            </div>

            <div className="flex gap-4">
                <button> <ShoppingCart size={46} /> </button>
                <UserDropdown />
            </div>
        </nav>
    )
}

export default Header;
