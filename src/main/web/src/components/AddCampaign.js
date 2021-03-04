import React, {useState} from 'react';

const AddCampaign = ({onAdd}) => {
    const [name, setName] = useState('');
    const [store, setStore] = useState('');
    const [price, setPrice] = useState(0);
    const [dateBegin, setDateBegin] = useState('');
    const [dateEnd, setDateEnd] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onAdd({name, store, price: +price, dateBegin, dateEnd});
        setName('');
        setStore('');
        setPrice(0);
        setDateBegin('');
        setDateEnd('');
    }


    return (
        <div className="form-wrapper">
            <form className="form-control" onSubmit={handleSubmit}>
                <div>
                <label>Name: </label>
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)} autoFocus/> 
                </div>
                <div>
                    <label>Store: </label>
                    <input type="text" value={store} onChange={(e) => setStore(e.target.value)}/> 
                </div>
                <div>
                    <label>Price: </label>
                    <input type="number" value={price} onChange={(e) => setPrice(e.target.value)}/> 
                </div>
                <div>
                    <label>Date from: </label>
                    <input type="date" value={dateBegin} onChange={(e) => setDateBegin(e.target.value)}/>
                        
                </div> 
                <div>
                    <label>to: </label>
                    <input type="date" value={dateEnd} onChange={(e) => setDateEnd(e.target.value)}/>
                </div>
                <div>        
                    <input className="btn btn-block" type="submit" value="Save"/>
                </div>
            </form>
        </div>
    )
}

export default AddCampaign
