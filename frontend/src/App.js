import './App.css';
import { RouterProvider } from 'react-router-dom';
import { QueryClientProvider, QueryClient } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import root from './router/root';

// Create a client
const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
        <RouterProvider router={root}/>
        <ReactQueryDevtools/>
    </QueryClientProvider>
  );
}

export default App;
