import { useEffect, useState } from 'react';
import { api, demoProducts, normalizeProduct } from '../api/client';

export function useProducts() {
  const [items, setItems] = useState(demoProducts);
  const [source, setSource] = useState('demo');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const refresh = async () => {
    try {
      const data = await api.products();
      setItems(data.map(normalizeProduct));
      setSource('database');
      setError('');
    } catch (err) {
      setSource('demo');
      setError(err.message || 'Backend unavailable; showing sample catalogue.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { refresh(); }, []);
  return { products: items, source, loading, error, refresh, setProducts: setItems };
}
