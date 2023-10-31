import { FormControl, Input } from '@mui/base'
import { Card, CardContent, CardMedia, Container, ImageList, ImageListItem, InputLabel, Typography } from '@mui/material'
import { Box } from '@mui/system'
import React, { useEffect, useState } from 'react'
import Button from '@mui/material/Button';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { mainAPi } from '../api';
import { useNavigate, useParams } from 'react-router-dom';

import { jwtDecode } from "jwt-decode";

export default function DetailProductLayout() {
  const params= useParams();
  const productId=params.id;

  const navigate=useNavigate();
  const [product,setProduct]=useState([]);
  const [isLogged,setLoggedStatus]=useState(false);
  const[image,setImage]=useState("");

 
  useEffect(()=>{
    async function fetchProduct(){
      try {
        const response=await mainAPi.get(`/product-service/detail/${productId}`)
        setProduct(response.data);
      } catch (error) {
        console.log(error);
      }
    }
    
    fetchProduct();

  },[]);
 
  
  const itemData = [
    {
      img: 'https://images.unsplash.com/photo-1551963831-b3b1ca40c98e',
      title: 'Breakfast',
    },
    {
      img: 'https://images.unsplash.com/photo-1551782450-a2132b4ba21d',
      title: 'Burger',
    },
    {
      img: 'https://images.unsplash.com/photo-1522770179533-24471fcdba45',
      title: 'Camera',
    },
      {
        img: 'https://images.unsplash.com/photo-1444418776041-9c7e33cc5a9c',
        title: 'Coffee',
      },
      {
        img: 'https://images.unsplash.com/photo-1533827432537-70133748f5c8',
        title: 'Hats',
      },
      {
        img: 'https://images.unsplash.com/photo-1558642452-9d2a7deb7f62',
        title: 'Honey',
      },
      {
        img: 'https://images.unsplash.com/photo-1516802273409-68526ee1bdd6',
        title: 'Basketball',
      }
    ];
    
 const addToCart=()=>{
  
  const token=jwtDecode(localStorage.getItem("token"));
  console.log(token.sub);

  mainAPi.post("/cart-service/add-product", {
    "id":productId,
    "userId":token.sub
  }
//   ,{
//     headers: {
//       Accept: "application/json",
//       Authorization: `Bearer ${localStorage.getItem("token")}`,
      
//     }
// }
)
  .then((result)=>{
    console.log(result.data);
    navigate("/cart");
  })
  .catch((err)=>{
    console.log(err);
  })
}
    const renderProduct=(data)=>{
       
        if(data){
          const{content}=data;
         
          return(
            <>
              <Card 
        
        sx={{
          maxWidth:400,
          flex:1
        }}>
         
          <CardMedia
          sx={{ height: 350,
          }}
         
          image={content&& image===""?content.imageUrl:image}
          title="green iguana"
        />
        </Card>
        <Box  paddingX="30px"
        
          sx={{
            maxWidth:"400px"
          }}
        >
            <Typography
            
            color="rgba(91,33,182)"
              variant='h3'
              fontWeight="bold"
            >
              {content && content.name}
            </Typography>
            <Typography
              variant='body1'
              // marginY="12px"
              marginTop='8px'
              marginBottom="4px"
            >
             It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-o
            </Typography>
            <Typography 
            
              variant='h6'
              color="rgba(91,33,182)"
            >
              ${content && content.price}
            </Typography>
            <Box
              
            paddingY='10px'
            >            
               <Button 
                variant='contained'
                endIcon={<ShoppingCartIcon/>}
                onClick={()=>addToCart()}
               >
                Add to cart
               </Button>
             
            </Box>
            <Box>
            <ImageList
            sx={{
              display: 'flex',
              justifyContent: 'center',
             
            }}
           
            
            >
                 {itemData.map((item) => (
          <ImageListItem key={item.img}
                  sx={{
                    cursor:'pointer'
                  }}
          >
            <img
              srcSet={`${item.img}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
              src={`${item.img}?w=164&h=164&fit=crop&auto=format`}
              alt={item.title}
              onClick={()=>{
                setImage(item.img)
              }}
              loading="lazy"
            />
          </ImageListItem>
        ))}
            </ImageList>
            </Box>
            
        </Box>
            </>
          )
        }
    }
    return (
      <Container 
      maxWidth="md"
  
      sx={{
        paddingY:"100px",
        display:'flex'
      }}>
        
        {renderProduct(product)}
      </Container>
    )
  }
  