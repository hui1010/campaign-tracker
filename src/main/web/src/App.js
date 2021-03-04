import React, {useState} from 'react';
import axios from 'axios';

import './App.css';
import Header from './components/Header';
import Campaigns from './components/Campaigns';
import ShoppingList from './components/ShoppingList';
import AddCampaign from './components/AddCampaign';
import AddShoppingItem from './components/AddShoppingItem';


function App() {

  const [campaigns, setCampaigns] = useState([]);
  const [shoppingList, setShoppingList] = useState([]);


  const addCampaign = (campaign) => {
    const id = Math.floor(Math.random()*10000);
    const newCampaign = {id, ...campaign};
    setCampaigns([...campaigns, newCampaign]);
  }

  const handleCampaignEdit = (id) => {
    console.log("Editing", id);
  }

  const handleCampaignDelete = (id) => {
    setCampaigns(campaigns.filter((campaign) => campaign.id !== id));
  }


  const addShoppingItem = (item) => {
    const id = Math.floor(Math.random()*10000);
    const newItem = {id, ...item};
    setShoppingList([...shoppingList, newItem]);

    // axios.post(`https://campaign-tracker-huiyi.herokuapp.com/api/items`, {
    //   method: 'POST',
    //   headers: {
    //     "Accept":"*/*",
    //     "Content-Type": "application/json"
    //   },
    //   body: JSON.stringify(item)  
    // })
    //   .then(response =>response.json())
    //   .then(data => setShoppingList([...shoppingList, data]))
  }

  const hanldeListEdit = (id) => {
    console.log("-----------Edit", id );
  }

  const handleListDelete = (id) => {
    setShoppingList(shoppingList.filter(item => item.id !== id));
  }

  const sortByEndDate = () => {
    const sortedCampaigns = campaigns.slice().sort((a, b) => new Date(a.dateEnd) - new Date(b.dateEnd));
    setCampaigns(sortedCampaigns); 
  }

  const sortByPrice = () => {
    const sortedCampaigns = campaigns.slice().sort((a, b) => a.price - b.price);
    setCampaigns(sortedCampaigns);  
  }

  const sortByName = () => {
    const sortedCampaigns = campaigns.slice().sort((a, b) => a.name.toLowerCase().localeCompare(b.name.toLowerCase()));
    setCampaigns(sortedCampaigns);
  }

  const sortByStore = () => {
    const sortedCampaigns = campaigns.slice().sort((a, b) => a.store.toLowerCase().localeCompare(b.store.toLowerCase()));
    setCampaigns(sortedCampaigns);
  }

  const [matched, setMatched] = useState([]);

  const matchItem = (name) => {
    const matchingCampaigns = campaigns.slice().filter((campaign) => campaign.name.toLowerCase().includes(name.toLowerCase()));
    setMatched(matchingCampaigns)
    setCampaigns(campaigns) 
  }

  const matchAll = (names) => {
    let matchingCampaigns = []
    names.map((name) => {
      matchingCampaigns.push(campaigns.slice().filter((campaign) => campaign.name.toLowerCase().includes(name.toLowerCase()))) ;
    }) 
    setMatched(matchingCampaigns.flat(Infinity))
    setCampaigns(campaigns)
  }

  const cancelMatch = () => {
    setMatched([])
  }

  const [showAddCampaign, setShowAddCampaign] = useState(false);
  const [showAddShoppingItem, setShowAddShoppingItem] = useState(false);

  const showCampaignForm = () => {
    setShowAddCampaign(!showAddCampaign);
  }

  const showShoppingForm = () => {
    setShowAddShoppingItem(!showAddShoppingItem);
  }

  
  return (
    <div className="container">
      <div className="campaigns">
      <Header text="Campaigns" onAdd={showCampaignForm} showAdd={showAddCampaign}/>
      {showAddCampaign && <AddCampaign onAdd={addCampaign}/>}
      {campaigns.length > 0 ? <Campaigns campaigns={campaigns} 
        onEdit={handleCampaignEdit} 
        onDelete={handleCampaignDelete}
        sortByEndDate={sortByEndDate}
        sortByPrice={sortByPrice}
        sortByName={sortByName}
        sortByStore={sortByStore}
        matched={matched}/> : 
      "Click the Add button to add campaigns :)"}
      </div>
      <div className="shopping-list">
      <Header text="Shopping list" onAdd={showShoppingForm} showAdd={showAddShoppingItem} />
      {showAddShoppingItem && <AddShoppingItem onAdd={addShoppingItem}/>}
      {shoppingList.length > 0 ? <ShoppingList list={shoppingList} matched={matched} onEdit={hanldeListEdit} onDelete={handleListDelete} onMatchAll={matchAll} onMatchItem={matchItem} onCancelMatch={cancelMatch}/> : "Click the Add button to add shopping item :)"}
      </div>
    </div>
  );
}

export default App;
