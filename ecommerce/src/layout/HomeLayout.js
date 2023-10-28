import React, { useEffect, useState } from 'react'
import NavBar from '../components/Navbar'
import ListProducts from '../components/ListProducts'
import { mainAPi } from '../api'


export default function  HomeLayout() {
  const [products, setProducts] = useState([]);
useEffect(()=>{
  async function fetchProducts() {
    try {
      const response = await mainAPi.get('/product-service/products');
      setProducts(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  fetchProducts();
},[]);
 
 
 
 
  return (
    <div>
     
      <ListProducts products={products}/>
    </div>
  )
}
