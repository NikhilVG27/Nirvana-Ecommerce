import { products as demoProducts } from '../data/products';

const API_BASE = (import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api').replace(/\/$/, '');

async function request(path, options = {}) {
  const response = await fetch(`${API_BASE}${path}`, {
    ...options,
    headers: { 'Content-Type': 'application/json', ...(options.headers || {}) },
  });
  if (!response.ok) {
    let message = `API request failed (${response.status})`;
    try { const body = await response.json(); message = body.message || body.error || message; } catch { /* response has no JSON body */ }
    throw new Error(message);
  }
  if (response.status === 204) return null;
  return response.json();
}

export const api = {
  health: () => request('/health'),
  products: async (params = {}) => {
    const query = new URLSearchParams(params).toString();
    return request(`/products${query ? `?${query}` : ''}`);
  },
  createProduct: (product) => request('/products', { method: 'POST', body: JSON.stringify(product) }),
  deleteProduct: (id) => request(`/products/${id}`, { method: 'DELETE' }),
  placeOrder: (order) => request('/orders', { method: 'POST', body: JSON.stringify(order) }),
};

export function normalizeProduct(product) {
  return {
    ...product,
    id: Number(product.id),
    price: Number(product.price),
    oldPrice: product.oldPrice == null ? null : Number(product.oldPrice),
    rating: Number(product.rating || 0),
    sizes: Array.isArray(product.sizes) ? product.sizes : [],
    stock: Number(product.stock ?? 0),
  };
}

export { demoProducts };
