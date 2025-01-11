import { ShoppingCart } from 'lucide-react';
import { useState } from 'react';

function BuyButton(){
    const [productQt, setProductQt] = useState(1);

    return(
        <div className="flex gap-5">
            <div className="bg-gray-300 w-[5em] h-[2em] flex justify-center items-center gap-4 text-2xl rounded-2xl">
                <button onClick={() => setProductQt((productQt) => productQt - 1)}>-</button>
                <p>{productQt}</p>
                <button onClick={() => setProductQt((productQt) => productQt + 1)}>+</button>
            </div>
            <div className="bg-dark-purple w-[7em] h-[2em] text-white flex justify-center items-center gap-1 text-2xl rounded-2xl hover:bg-light-purple2">
                <button>Comprar</button>
                <button> <ShoppingCart size={32} /> </button>
            </div>
        </div>
    )
}

export default BuyButton

