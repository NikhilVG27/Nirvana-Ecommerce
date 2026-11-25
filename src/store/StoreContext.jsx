import {api} from '../api/client';
import {createContext,useContext,useEffect,useMemo,useState} from 'react';
const StoreContext=createContext(null);
const read=(key,fallback)=>{try{return JSON.parse(localStorage.getItem(key))??fallback}catch{return fallback}};
export function StoreProvider({children}){
 const [cart,setCart]=useState(()=>read('nirvana-cart',[]));
 const [wishlist,setWishlist]=useState(()=>read('nirvana-wishlist',[]));
 const [user,setUser]=useState(()=>read('nirvana-user',null));
 const [orders,setOrders]=useState(()=>read('nirvana-orders',[]));
 useEffect(()=>localStorage.setItem('nirvana-cart',JSON.stringify(cart)),[cart]);
 useEffect(()=>localStorage.setItem('nirvana-wishlist',JSON.stringify(wishlist)),[wishlist]);
 useEffect(()=>localStorage.setItem('nirvana-user',JSON.stringify(user)),[user]);
 useEffect(()=>localStorage.setItem('nirvana-orders',JSON.stringify(orders)),[orders]);
 const addToCart=(product, size=product.sizes?.[0]||'ONE SIZE')=>setCart(old=>{const found=old.find(x=>x.id===product.id&&x.size===size);return found?old.map(x=>x.id===product.id&&x.size===size?{...x,qty:x.qty+1}:x):[...old,{...product,size,qty:1}]});
 const changeQty=(id,size,qty)=>setCart(old=>qty<1?old.filter(x=>!(x.id===id&&x.size===size)):old.map(x=>x.id===id&&x.size===size?{...x,qty}:x));
 const removeFromCart=(id,size)=>setCart(old=>old.filter(x=>!(x.id===id&&x.size===size)));
 const toggleWishlist=p=>setWishlist(old=>old.some(x=>x.id===p.id)?old.filter(x=>x.id!==p.id):[...old,p]);
 const placeOrder=async details=>{const result=await api.placeOrder({...details,items:cart.map(item=>({productId:item.id,quantity:item.qty,size:item.size}))});const order={id:result.orderNumber,items:cart,amount:Number(result.total),status:result.status,date:new Date(result.createdAt).toLocaleDateString('en-IN'),...details,backendSaved:true};setOrders(old=>[order,...old]);setCart([]);return order};
 const value=useMemo(()=>({cart,wishlist,user,orders,setUser,addToCart,changeQty,removeFromCart,toggleWishlist,placeOrder}),[cart,wishlist,user,orders]);
 return <StoreContext.Provider value={value}>{children}</StoreContext.Provider>;
}
export const useStore=()=>useContext(StoreContext);