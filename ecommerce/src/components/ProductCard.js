import { Button, Card, CardActions, CardContent, CardMedia, Typography } from '@mui/material'
import React, { useState } from 'react'
import { Link } from 'react-router-dom';

export default function ProductCard({info}) {
  
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
        <Button>Add to cart</Button>
      </CardActions>
    </Card>
  )
}
