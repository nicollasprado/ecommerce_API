import { useState } from 'react'
import BreadCrumb from "../components/BreadCrumb.jsx"
import Title from "../components/Title.jsx"
import Price from "../components/Price.jsx"
import BuyButton from "../components/BuyButton.jsx"
import ShippingCalc from "../components/ShippingCalc"
import { useSearchParams } from "react-router"
import useProductData from "../hooks/useProductData.js"


function ProductPage() {
    const [searchParams] = useSearchParams();
    const productId = searchParams.get("productId");
    const { data, isLoading, isError, error } = useProductData(productId);

    if(isLoading){
        return <div>Carregando...</div>
    }

    if(isError){
        return <div>Erro ao carregar produto {error.message}</div>
    }

    if(!data){
        return <div>Produto não encontrado</div>
    }

    console.log(data);
    const installments = (data.price / 12).toFixed(2);

    return (
        <div className="ml-[9.5em] flex flex-col gap-[5em]">
            <nav>
                <BreadCrumb path={["home", "computador", "placa de video"]} />
            </nav>
            <div className="flex gap-[4em]">
                <figure className="p-5 border border-black">
                    <img src={data.imgUrl} alt="imagem do produto" width="600em" />
                </figure>
                <div className="flex flex-col gap-4">
                    <Title>{data.name}</Title>
                    <div className="flex flex-col gap-6">
                        <div>
                            <Price currentPrice={"R$ " + data.price.toFixed(2)}
                                   oldPrice={"R$ " + (data.price * 1.2).toFixed(2)}
                                   installments={`em até 12x de R$ ${installments} sem juros`} />
                        </div>
                        <div className="pb-8 border-b-2 border-black">
                            <BuyButton />
                        </div>
                        <ShippingCalc></ShippingCalc>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ProductPage
