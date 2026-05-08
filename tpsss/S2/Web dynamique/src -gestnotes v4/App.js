import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "./pages/login.js";
import Register from "./pages/register.js";
import Logout from "./pages/logout.js";
import Welcome from "./pages/welcome.js";
import Notes from "./components/notes.js";
import Navbar from "./pages/navbar.js";
import Footer from "./pages/footer.js";
import Rqs from "./pages/rqs.js";

const routes = createBrowserRouter([
  { path: "/login", element: <Login /> },
  { path: "/register", element: <Register /> },
  { path: "/logout", element: <Logout /> },
  { path: "/welcome", element: <Welcome /> },
  { path: "/notes", element: <Notes /> },
  { path: "/navbar", element: <Navbar /> },
  { path: "/footer", element: <Footer /> },
  { path: "/rqs", element: <Rqs /> }
]);

function App() {
  return (
    <div>
      <RouterProvider router={routes} />
    </div>
  );
}

export default App;
