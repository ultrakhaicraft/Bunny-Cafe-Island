import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LandingPage from './components/LandingPage';
import Bunny from './components/Bunny';

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/bunnyShowcase" element={<Bunny />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
