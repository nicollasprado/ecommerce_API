import axios from "axios"
import { useQuery } from "@tanstack/react-query"

const API_URL = "http://localhost:8080"

const fetchData = async (productId) => {
    try{
        const response = await axios.get(API_URL + "/product/" + productId)
        return response.data;
    }catch (error) {
        console.log(error);
    }
}

export default function useProductData(productId){
    const query = useQuery({
        queryFn: () => fetchData(productId),
        queryKey: ['product-data', productId],
        retry: 2,
    })

    return query;
}
