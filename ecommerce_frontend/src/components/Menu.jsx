import { ChevronDown } from 'lucide-react';

function Menu(props){
    return (
        <button className="flex">
            {props.children}
            <ChevronDown />
        </button>
    )
}

export default Menu;
