
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css';
import NavBar from './components/Navbar';
import DetailProductLayout from './layout/DetailProductLayout';
import HomeLayout from './layout/HomeLayout';
const router = createBrowserRouter([
  {
    path: "/",
    element: <HomeLayout/>,
  },
  {
      path:"/Detail/:id",
      element: <DetailProductLayout/>
  }
]);
function App() {
  return (
    <div>
      <NavBar/>
    <RouterProvider
      router={router}
    />
    </div>
    

  );
}

export default App;
