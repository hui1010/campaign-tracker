import React, {useState} from 'react';
import { TiDelete } from 'react-icons/ti'
import { MdEdit } from 'react-icons/md'
import { MdSave } from "react-icons/md";
import { FcCancel } from "react-icons/fc";


const Campaign = ({campaign, matched, onEdit, onDelete}) => {
 
    const [editClicked, setEditClicked] = useState(false)
    const [cancelClicked, setCancelClicked] = useState(false)

    const [name, setName] = useState(campaign.name);
    const [store, setStore] = useState(campaign.store);
    const [amount, setAmount] = useState(campaign.amount);
    const [price, setPrice] = useState(campaign.price);
    const [dateBegin, setDateBegin] = useState(campaign.dateBegin ? campaign.dateBegin : '');
    const [dateEnd, setDateEnd] = useState(campaign.dateEnd ? campaign.dateEnd : '');


    const handleEdit = () => {
       setEditClicked(true) 
        
    }

    const handleSubmit = async () => {
        const edited = await onEdit({"campaignId": campaign.campaignId, name, store, amount: +amount, price: +price, dateBegin, dateEnd})
        if(edited) {
            setEditClicked(false)
        }
    }

    const handleCancel = () => {
        setCancelClicked(true)
        setEditClicked(false)
        setName(campaign.name)
        setStore(campaign.store)
        setAmount(campaign.amount)
        setPrice(campaign.price)
        setDateBegin(campaign.dateBegin)
        setDateEnd(campaign.dateEnd)
    }

    return (     
         <tr className={matched.includes(campaign) ? 'campaign highlight' : 'campaign'}>
            <td>{editClicked? <input type="text" value={name} onChange={e => setName(e.target.value)}/> : cancelClicked? campaign.name :name}</td>
            <td>{editClicked? <input type="text" value={store} onChange={e => setStore(e.target.value)}/> : cancelClicked? campaign.store : store}</td>
            <td>{editClicked? <input type="number" value={amount} onChange={e => setAmount(e.target.value)} /> : cancelClicked? campaign.amount : amount}</td>
            <td>{editClicked? <input type="number" value={price} onChange={e => setPrice(e.target.value)} /> : cancelClicked? campaign.price : price}</td>
            <td>{editClicked? <input type="date" value={dateBegin} onChange={e => setDateBegin(e.target.value)} /> : cancelClicked? campaign.dateBegin : dateBegin}</td>
            <td>{editClicked? <input type="date" value={dateEnd} onChange={e => setDateEnd(e.target.value)} /> : cancelClicked? campaign.dateEnd : dateEnd}</td>
            <td>{editClicked? <MdSave color="grey" size="2rem" onClick={handleSubmit}/> : <MdEdit color="grey" size="2rem" onClick={handleEdit}/>}
            {editClicked? <FcCancel size="2rem"  onClick={handleCancel}/> : <TiDelete color="red" size="2rem"  onClick={() => onDelete(campaign.campaignId)}/>}</td>  
        </tr> 
    )
}

export default Campaign
