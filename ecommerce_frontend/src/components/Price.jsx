function Price( {currentPrice, oldPrice, installments} ){
    return(
        <div>
            <h3 className="text-[1.1em] text-gray-500 italic line-through">
                {oldPrice}
            </h3>
            <h2 className="text-[1.3em] font-bold text-dark-purple">
                {currentPrice}
            </h2>
            <p>{installments}</p>
        </div>
    )
}

export default Price
