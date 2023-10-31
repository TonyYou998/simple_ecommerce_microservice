import { Button, Card, CardActions, CardContent, CardMedia, Typography } from '@mui/material'
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';
import { mainAPi } from '../api';
export default function ProductCard({info}) {
    const navigate=useNavigate()
    const [imageScale,setImageScale]=useState(1);
    const handleHoverIn=()=>{
        setImageScale(1.2)
    }
    const handleHoverOut=()=>{
        setImageScale(1)
    }
    
  return (
    <Card onMouseEnter={handleHoverIn} onMouseLeave={handleHoverOut}>
      <Link to={`/detail/${info.id}`}   style={{textDecoration: 'none'}}>
      <CardMedia
            className='card__image'
             sx={{ height: 140,transform:`scale(${imageScale})`, transition:'transform 0.3s ease' }}
             image={info.imageUrl}
             title="Product Name"
           
        />
        <CardContent>
        <Typography gutterBottom variant="h5" component="div"
          
        >
            
          {info.name}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {info.description}
        </Typography>
      </CardContent >
      </Link>
       
      <CardActions sx={
        {
          justifyContent:'space-between'
        
        }
      }>
        <Typography 
          variant='subtitle1'
          
        >${info.price}</Typography>
        <Button onClick={()=>{
          const token=jwtDecode(localStorage.getItem("token"));

           mainAPi.post("/cart-service/add-product", {
            "id":info.id,
            userId:token.sub
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
        }}>Add to cart</Button>
      </CardActions>
    </Card>
  )
}
