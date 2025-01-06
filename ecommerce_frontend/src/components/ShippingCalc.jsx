function ShippingCalc(){
    return(
        <div className="flex flex-col gap-3">
            <h1>Consultar Frete:</h1>
            <div className="flex">
                <input
                    type="text"
                    maxLength="8" minLength="8"
                    placeholder="00000-000"
                    className="w-[8em] h-[3em] text-white bg-gray-400 outline-none p-5 placeholder-white rounded-l-2xl"/>
                <button className="text-white bg-light-purple w-[3em] rounded-r-xl hover:bg-light-purple2">OK</button>
            </div>
        </div>
    )
}

export default ShippingCalc
