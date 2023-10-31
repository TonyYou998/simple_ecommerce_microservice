import { FormControl, Input } from '@mui/base'
import { Card, CardContent, CardMedia, Container, IconButton, ImageList, ImageListItem, InputLabel, Typography } from '@mui/material'
import { Box } from '@mui/system'
import React, { useEffect, useState } from 'react'
import Button from '@mui/material/Button';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { mainAPi } from '../api';
import { useParams } from 'react-router-dom';
import AddIcon from '@mui/icons-material/Add';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Link } from 'react-router-dom';

import {jwtDecode}from "jwt-decode";
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import RemoveIcon from '@mui/icons-material/Remove';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
let total=0;
function createData(product,quantity,price) {
    return { product,quantity,price };
  }

  const rows = [
    createData('Frozen yoghurt', 159, 6.0),
    createData('Ice cream sandwich', 237, 9.0),
    createData('Eclair', 262, 16.0),
    createData('Cupcake', 305, 3.7,),
    createData('Gingerbread', 356, 16.0,),
  ];

export default function CartLayout() {
  const [qua,setQua]=useState(0);
    const[items,setItems]=useState();
    useEffect(()=>{

        async function fetchItem(){
          const token=jwtDecode(localStorage.getItem("token"));
          const userId=token.sub;
            try {
              const response=await mainAPi.get(`/cart-service/cart/${userId}`)
              setItems(response.data);
            } catch (error) {
              console.log(error);
            }
          }
          
          fetchItem();
    },[]);
 const handleAddItem=(item)=>{
  
    mainAPi.post("/cart-service/cart/add-item",item)
    .then((result)=>{
      const{content}=result.data;
      setQua(content.quantity);
      // console.log(content);
      item.quantity++;
    })
    .catch((err)=>{
        console.log(err);
    })
 }
 const handleRemoveItem=(item)=>{
  
  mainAPi.post("/cart-service/cart/remove-item",item)
  .then((result)=>{
    const{content}=result.data;
    console.log(content);
    setQua(content.quantity);
    // console.log(content);
    item.quantity++;
  })
  .catch((err)=>{
      console.log(err);
  })
}
    const renderItem=(data)=>{
           console.log(qua);
            if(data){
                const {content}=data;
                    
              return content.map((item,index)=>{
                
                  total+=parseInt(qua)*parseFloat(item.price);
                return (
                    <TableRow key={index}>
                      <Link  to={`/detail/${item.productId}`} style={{textDecoration: 'none'}}>
                      <TableCell component="th" align='center' scope="row" sx={{
                        display:"flex",
                        alignItems:"center",
                        justifyContent:"space-around"
                      }}>
                    <img src={item.imageUrl} width="75" height="75" />
                   
                    <Typography
                      variant='subtitle1'
                     
                      
                    >
                      {item.productName}
                    </Typography>
                    </TableCell>
                      </Link>
                    
                    <TableCell align="center">{qua===0?item.quantity:qua}</TableCell>
                    <TableCell align="center">{parseFloat(item.price)*parseInt(item.quantity)}$</TableCell>
                    <TableCell align='center'>
                      <IconButton color='secondary'  onClick={()=>handleAddItem(item)}>
                        <AddIcon/>
                      </IconButton>
                      <IconButton color='secondary'
                        onClick={()=>handleRemoveItem(item)}
                      >
                        <RemoveIcon/>
                      </IconButton>
                    </TableCell>
                  </TableRow>
                )

              })  
            }
    }
 
    return (
        <Container maxWidth="md"
            sx={{
                paddingTop:"100px"
            }}
        >
                <Box>
                    <Typography
                        variant='h4'
                        textAlign="center"
                        color="rgba(91,33,182)"
                        fontWeight="bold"
                    >
                        Your Cart
                    </Typography>
                    <TableContainer component={Paper} 
                      
                    >
      <Table sx={{ minWidth: 650 }} aria-label="caption table">
       
        <TableHead>
          <caption></caption>
          <TableRow>
            <TableCell align='center'>Product</TableCell>
            <TableCell align="center">Quantity</TableCell>
            <TableCell align="center">Price</TableCell>
            <TableCell align='center'>
              +/-
            </TableCell>

          </TableRow>
        </TableHead>
        <TableBody>
            {
                renderItem(items)
            }
            <TableRow>
              <TableCell></TableCell>
              <TableCell>
                <Link to="/">
                <Button 
                  variant='contained'
                  startIcon={<ArrowBackIcon/>}
                  size='medium'
                  // color='rgba(255,255,255)'
                >
                  Back To Shop
                </Button>
                </Link>
                </TableCell>
              <TableCell align='center'
                
                >
                  <Typography
                    variant='h6'
                    color="#5b21b6"
                    fontWeight="bold"
                  >
                       ${total}
                  </Typography>
              </TableCell>
              <TableCell align='center'>
                <Button 
                  variant='contained'
                  endIcon={<ArrowForwardIcon/>}
                  size='medium'
                  // color='rgba(255,255,255)'
                >
                  Checkout
                </Button>
              </TableCell>
             
            </TableRow>
        </TableBody>
           
      </Table>
    </TableContainer>
   
                </Box>
        </Container>
    )
  }
  