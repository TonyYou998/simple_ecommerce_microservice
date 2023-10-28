import React from 'react'
import { Container } from '@mui/system'
import { Grid, Typography } from '@mui/material'
import { experimentalStyled as styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import ProductCard from './ProductCard';
const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));
  
  const renderItem=(products)=>{
    const{content}=products;
      if(content){
     
    
       return  content.map((item,index)=>{
          return(<Grid key={index} item xs={4} sm={6} md={3}>
            <ProductCard info={item} />
        </Grid>);
          
        })  
      }
  }
export default function ({products}) {
  
  return (
    <div>
        <Container fixed
            sx={{
                marginY:'100px'
            }}
        >
        <Grid container spacing={{md:3}} columns={{ xs: 4, sm: 8, md: 12 }}>
        {
          renderItem(products)
        }
        </Grid>
      </Container>
    </div>
  )
}
