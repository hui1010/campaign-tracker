import React, {useState, useEffect} from 'react';
import axios from 'axios';
import {toast} from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'

import './App.css';
import Header from './components/Header';
import Campaigns from './components/campaigns/Campaigns';
import ShoppingList from './components/shoppingList/ShoppingList';
import AddCampaign from './components/campaigns/AddCampaign';
import AddShoppingItem from './components/shoppingList/AddShoppingItem';
import PageTop from './components/PageTop';

import Footer from './components/Footer';

toast.configure()

function App() {

  const itemUrl = 'http://localhost:3001/api/items'
  const campaignUrl = 'http://localhost:3001/api/campaigns'
  const [campaigns, setCampaigns] = useState([]);
  const [shoppingList, setShoppingList] = useState([]);
  const [query, setQuery] = useState('')
  
  useEffect(() => {axios.get(itemUrl).then(response => setShoppingList(response.data))}, [])
  useEffect(() => {axios.get(`${campaignUrl}?type=${query? 'item' : 'all'}&value=${query}`).then(response => setCampaigns(response.data))}, [query])

  const addCampaign = (campaign) => {
    axios.post(campaignUrl, {
      dateBegin: campaign.dateBegin,
      dateEnd: campaign.dateEnd,
      name: campaign.name, 
      amount: campaign.amount,
      price: campaign.price,
      store: campaign.store
    }).then(response => {
      setCampaigns([...campaigns, response.data])
    })
      .catch(err => {
        if(err.response.status === 405) {
         toast.error('Campaign already exists.', {
           position: toast.POSITION.BOTTOM_CENTER
         })
        }
        if(err.response.status === 400) {
          toast.error('Invalid information has been filled in.', {
            position: toast.POSITION.BOTTOM_CENTER
          })
        }
      })
  }

  const handleCampaignEdit = async (campaign) => {
    let succeed = false
    await axios.put(campaignUrl, campaign)
    .then(response => {
      if(response.status === 200) {
        setCampaigns(campaigns.map(campaign => campaign.campaignId === response.data.campaignId ? {...campaign, name: response.data.name, store: response.data.store, amount: response.data.amount, price: response.data.price, dateBegin: response.data.dateBegin, dateEnd: response.data.dateEnd} : campaign))
        succeed = true
      }
    })
    .catch(err => {
      if(err.response.status === 405) {
        toast.error('Campaign already exists.', {
          position: toast.POSITION.BOTTOM_CENTER
        })
      }
      if(err.response.status === 400) {
        toast.error('End date should not be before begin date.', {
          position: toast.POSITION.BOTTOM_CENTER
        })
      }
    })
    return succeed
  }

  const handleCampaignDelete = (id) => {
    axios.delete(`${campaignUrl}/${id}`)
    setCampaigns(campaigns.filter((campaign) => campaign.campaignId !== id));
  }

  const addShoppingItem = (item) => {
//    const id = Math.floor(Math.random()*10000);
//    const newItem = {id, ...item};
//    setShoppingList([...shoppingList, newItem]);
     axios.post(itemUrl, {
        name: item.name
     })
     .then(response => {
       setShoppingList([...shoppingList, response.data])})
     .catch(() => {
       toast.error('Item already exists', {
         position: toast.POSITION.BOTTOM_CENTER
       })
     })
  }

  const handleListEdit = (item) => {
    axios.put(itemUrl, item)
    .then(response => {
      setShoppingList(shoppingList.map(item => item.shoppingItemId === response.data.shoppingItemId ? {...item, name: response.data.name} : item))
    })
    .catch( () => {
      toast.error('Item already exists', {
        position: toast.POSITION.BOTTOM_CENTER
      })
    }) 
  }

  const handleListDelete = (id) => {
    axios.delete(`${itemUrl}/${id}`)
    setShoppingList(shoppingList.filter(item => item.shoppingItemId !== id));
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
    const matchingCampaigns = campaigns.slice().filter((campaign) => campaign.name.toLowerCase().includes(name.toLowerCase()) 
    || name.toLowerCase().includes(campaign.name.toLowerCase()));
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

  const showMatchItems = (name) => {
    const matchingCampaigns = campaigns.slice().filter((campaign) => campaign.name.toLowerCase().includes(name.toLowerCase()));
    setCampaigns(matchingCampaigns)
  }

  const cancelMatch = () => {
    setMatched([])
  }

  const showAllCampaigns = () => {
    setCampaigns(campaigns)
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
    <>
      <div className="page-top img-responsive">
        {/* <PageTop /> */}
      </div>
      <div className="container">
      <div className="campaigns">
      <Header text="Campaigns" onAdd={showCampaignForm} showAdd={showAddCampaign}/>
      {showAddCampaign && <AddCampaign onAdd={addCampaign}/>}
      <Campaigns campaigns={campaigns} 
        onEdit={handleCampaignEdit} 
        onDelete={handleCampaignDelete}
        sortByEndDate={sortByEndDate}
        sortByPrice={sortByPrice}
        sortByName={sortByName}
        sortByStore={sortByStore}
        matched={matched}
        matchItem={matchItem}
        cancelMatch={cancelMatch}
        showMatchItems={showMatchItems}
        showAllCampaigns={showAllCampaigns}
        setQuery={q => setQuery(q)}/> 
      </div>
      <div className="vertical"></div>
      <div className="shopping-list">
      <Header text="Shopping list" onAdd={showShoppingForm} showAdd={showAddShoppingItem} />
      {showAddShoppingItem && <AddShoppingItem onAdd={addShoppingItem}/>}
      {shoppingList.length > 0 ? <ShoppingList list={shoppingList} matched={matched} onEdit={handleListEdit} onDelete={handleListDelete} onMatchAll={matchAll} onMatchItem={matchItem} onCancelMatch={cancelMatch}/> : <p className="blank-text">Click the Add button to add shopping item.</p>}
      </div>
      </div>
      <Footer />
    </>
  );
}

export default App;
