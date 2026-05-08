import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "./pages/login.js";
import Register from "./pages/register.js";
import Logout from "./pages/logout.js";
import Welcome from "./pages/welcome.js";
import Notes from "./components/notes.js";

const routes = createBrowserRouter([
  { path: "/login", element: <Login /> },
  { path: "/register", element: <Register /> },
  { path: "/logout", element: <Logout /> },
  { path: "/welcome", element: <Welcome /> },
  { path: "/notes", element: <Notes /> }  
]);

function App() {
  return (
    <div>
      <RouterProvider router={routes} />
    </div>
  );
}

export default App;
