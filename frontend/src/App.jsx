import { useMemo, useState } from 'react'
import { motion } from 'framer-motion'
import { ShoppingBag, Search, Menu, ArrowUpRight, Heart, Plus, Minus, X } from 'lucide-react'

const products = [
  { id: 1, name: 'Sculpted Linen Shirt', category: 'Men', price: 2499, tag: 'BESTSELLER', image: 'https://images.unsplash.com/photo-1596755094514-f87e34085b2c?auto=format&fit=crop&w=900&q=85' },
  { id: 2, name: 'The Everyday Tote', category: 'Women', price: 1899, tag: 'NEW ARRIVAL', image: 'https://images.unsplash.com/photo-1590874103328-eac38a683ce7?auto=format&fit=crop&w=900&q=85' },
  { id: 3, name: 'Minimal Form Jacket', category: 'Men', price: 4299, tag: 'LIMITED', image: 'https://images.unsplash.com/photo-1591047139829-d91aecb6caea?auto=format&fit=crop&w=900&q=85' },
  { id: 4, name: 'Soft Structure Dress', category: 'Women', price: 3299, tag: 'EDITOR PICK', image: 'https://images.unsplash.com/photo-1539109136881-3be0616acf4b?auto=format&fit=crop&w=900&q=85' },
  { id: 5, name: 'Classic Everyday Sneaker', category: 'Accessories', price: 2799, tag: 'POPULAR', image: 'https://images.unsplash.com/photo-1549298916-b41d501d3772?auto=format&fit=crop&w=900&q=85' },
  { id: 6, name: 'Modern Crossbody', category: 'Accessories', price: 2199, tag: 'NEW ARRIVAL', image: 'https://images.unsplash.com/photo-1584917865442-de89df76afd3?auto=format&fit=crop&w=900&q=85' }
]

export default function App() {
  const [category, setCategory] = useState('All')
  const [cart, setCart] = useState([])
  const [search, setSearch] = useState('')
  const [cartOpen, setCartOpen] = useState(false)
  const visible = useMemo(() => products.filter(p => (category === 'All' || p.category === category) && p.name.toLowerCase().includes(search.toLowerCase())), [category, search])
  const count = cart.reduce((n, item) => n + item.qty, 0)
  const total = cart.reduce((n, item) => n + item.price * item.qty, 0)
  const add = p => setCart(old => old.some(x => x.id === p.id) ? old.map(x => x.id === p.id ? {...x, qty:x.qty+1} : x) : [...old, {...p, qty:1}])
  const change = (id, amount) => setCart(old => old.map(x => x.id === id ? {...x, qty:x.qty+amount} : x).filter(x => x.qty > 0))

  return <div>
    <div className="announcement">COMPLIMENTARY SHIPPING ON ORDERS ABOVE ₹2,999</div>
    <header className="nav">
      <button className="icon-btn mobile-menu" aria-label="Menu"><Menu /></button>
      <a className="logo" href="#">NIRVANA<span>®</span></a>
      <nav><a href="#shop">Shop</a><a href="#collections">Collections</a><a href="#story">Our story</a></nav>
      <div className="nav-actions">
        <label className="search"><Search size={17}/><input value={search} onChange={e=>setSearch(e.target.value)} placeholder="Search pieces"/></label>
        <button className="icon-btn bag" onClick={()=>setCartOpen(true)} aria-label="Open cart"><ShoppingBag/><b>{count}</b></button>
      </div>
    </header>

    <main>
      <section className="hero">
        <img src="https://images.unsplash.com/photo-1483985988355-763728e1935b?auto=format&fit=crop&w=2000&q=90" alt="Contemporary fashion collection"/>
        <div className="hero-shade"/>
        <motion.div className="hero-copy" initial={{opacity:0,y:25}} animate={{opacity:1,y:0}} transition={{duration:.8}}>
          <p className="eyebrow gold">THE NEW WAY TO BE YOU</p>
          <h1>Style is a<br/><i>state of mind.</i></h1>
          <p>Thoughtful essentials. Considered design.<br/>Made for the way you move.</p>
          <a className="gold-btn" href="#shop">EXPLORE COLLECTION <ArrowUpRight size={17}/></a>
        </motion.div>
        <div className="hero-index">01 / 03</div>
      </section>

      <section id="collections" className="collections section-wrap">
        <div className="section-heading"><div><p className="eyebrow">CURATED FOR EVERY YOU</p><h2>Find your <i>form.</i></h2></div><p>Distinct expressions, one point of view.</p></div>
        <div className="collection-grid">
          <a className="collection-card women" href="#shop" onClick={()=>setCategory('Women')}><img src="https://images.unsplash.com/photo-1485230895905-ec40ba36b9bc?auto=format&fit=crop&w=1000&q=85" alt="Women's collection"/><span>01 — WOMEN</span><strong>Effortless<br/>expression <ArrowUpRight/></strong></a>
          <a className="collection-card men" href="#shop" onClick={()=>setCategory('Men')}><img src="https://images.unsplash.com/photo-1516257984-b1b4d707412e?auto=format&fit=crop&w=1000&q=85" alt="Men's collection"/><span>02 — MEN</span><strong>Modern<br/>essentials <ArrowUpRight/></strong></a>
          <a className="collection-card accessories" href="#shop" onClick={()=>setCategory('Accessories')}><img src="https://images.unsplash.com/photo-1490481651871-ab68de25d43d?auto=format&fit=crop&w=1000&q=85" alt="Accessories collection"/><span>03 — ACCESSORIES</span><strong>The finishing<br/>touch <ArrowUpRight/></strong></a>
        </div>
      </section>

      <section id="shop" className="shop-section">
        <div className="section-wrap">
          <div className="section-heading"><div><p className="eyebrow">THE CURRENT EDIT</p><h2>Pieces to <i>keep.</i></h2></div><p>Designed to outlast the moment.</p></div>
          <div className="filters">{['All','Women','Men','Accessories'].map(c=><button key={c} className={category===c?'active':''} onClick={()=>setCategory(c)}>{c}</button>)}</div>
          <div className="product-grid">{visible.map((p,i)=><motion.article className="product" key={p.id} initial={{opacity:0,y:20}} whileInView={{opacity:1,y:0}} viewport={{once:true}} transition={{delay:i*.06}}><div className="product-image"><img src={p.image} alt={p.name}/><span>{p.tag}</span><button onClick={()=>add(p)} aria-label={`Add ${p.name} to bag`}><Plus/></button></div><div className="product-meta"><div><h3>{p.name}</h3><p>{p.category}</p></div><strong>₹{p.price.toLocaleString('en-IN')}</strong></div></motion.article>)}</div>
          {visible.length===0 && <p className="empty">No pieces found. Try another search.</p>}
        </div>
      </section>

      <section id="story" className="story"><div><p className="eyebrow gold">LESS, BUT BETTER</p><h2>Make room for<br/><i>what matters.</i></h2><p>We believe great style is personal, purposeful and never overcomplicated.</p><a href="#shop" className="text-link">DISCOVER NIRVANA <ArrowUpRight size={16}/></a></div><div className="story-image"><img src="https://images.unsplash.com/photo-1441986300917-64674bd600d8?auto=format&fit=crop&w=1100&q=85" alt="Nirvana design studio"/></div></section>
    </main>

    <footer><a className="logo" href="#">NIRVANA<span>®</span></a><p>Considered style. Everyday life.</p><small>© 2026 NIRVANA. ALL RIGHTS RESERVED.</small></footer>

    {cartOpen && <div className="drawer-backdrop" onClick={()=>setCartOpen(false)}><aside className="cart-drawer" onClick={e=>e.stopPropagation()}><div className="drawer-head"><h2>Your bag ({count})</h2><button className="icon-btn" onClick={()=>setCartOpen(false)}><X/></button></div>{cart.length===0?<p className="empty">Your bag is waiting for something good.</p>:<>{cart.map(item=><div className="cart-item" key={item.id}><img src={item.image} alt=""/><div className="cart-info"><b>{item.name}</b><span>₹{item.price.toLocaleString('en-IN')}</span><div className="quantity"><button onClick={()=>change(item.id,-1)}><Minus size={14}/></button>{item.qty}<button onClick={()=>change(item.id,1)}><Plus size={14}/></button></div></div></div>)}<div className="cart-total"><span>Subtotal</span><b>₹{total.toLocaleString('en-IN')}</b></div><button className="gold-btn checkout" onClick={()=>alert('Checkout will be connected in a later phase.')}>CONTINUE TO CHECKOUT <ArrowUpRight size={17}/></button></>}</aside></div>}
  </div>
}
