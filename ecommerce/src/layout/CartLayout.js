import { FormControl, Input } from '@mui/base'
import { Card, CardContent, CardMedia, Container, ImageList, ImageListItem, InputLabel, Typography } from '@mui/material'
import { Box } from '@mui/system'
import React, { useEffect, useState } from 'react'
import Button from '@mui/material/Button';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { mainAPi } from '../api';
import { useParams } from 'react-router-dom';

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
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
    const[items,setItems]=useState();
    useEffect(()=>{
        async function fetchItem(){
            try {
              const response=await mainAPi.get("/cart-service/cart/1")
              setItems(response.data);
            } catch (error) {
              console.log(error);
            }
          }
          
          fetchItem();
    },[]);
 
    const renderItem=(data)=>{
           
            if(data){
                const {content}=data;
                console.log(content);     
              return content.map((item,index)=>{
                return (
                    <TableRow key={index}>
                    <TableCell component="th" scope="row">
                    {item.productName}
                    </TableCell>
                    <TableCell align="right">{item.quality}</TableCell>
                    <TableCell align="right">{item.price}</TableCell>
                   
                  </TableRow>
                )

              })  
            }
    }
   
    return (
        <Container maxWidth="md"
            sx={{
                paddingTop:"170px"
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
                    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="caption table">
       
        <TableHead>
          <TableRow>
            <TableCell>Product</TableCell>
            <TableCell align="right">Quantity</TableCell>
            <TableCell align="right">Price</TableCell>

    
          </TableRow>
        </TableHead>
        <TableBody>
            {
                renderItem(items)
            }
        </TableBody>
      </Table>
    </TableContainer>
                </Box>
        </Container>
    )
  }
  