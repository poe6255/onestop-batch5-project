
import './App.css'
import TopNav from './components/TopNav'
import Featured from './components/Featured'
import Delivery from './components/Delivery'
import TopPick from './components/TopPick'
import Meal from './components/Meal'
import Categories from './components/Categories'
import NewsLetter from './components/NewsLetter'
import Footer from './components/Footer'


function App() {
  return (
    <div>
       <TopNav></TopNav>
       <Featured></Featured>
       <Delivery></Delivery>
       <TopPick></TopPick>
       <Meal></Meal>
       <Categories></Categories>
       <NewsLetter></NewsLetter>
       <Footer></Footer>
    </div>
  );
}

export default App
