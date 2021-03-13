import React, {useState, useRef} from 'react';

const AddCampaign = ({onAdd}) => {
    const [name, setName] = useState('');
    const [store, setStore] = useState('');
    const [amount, setAmount] = useState(1);
    const [price, setPrice] = useState(0);
    const [dateBegin, setDateBegin] = useState('');
    const [dateEnd, setDateEnd] = useState('');
    const [error, setError] = useState('')

    const nameInput = useRef(null)

    const handleSubmit = (e) => {
        e.preventDefault();

        if(!(name && store && dateBegin && dateEnd)) {
            setError('All the fields need to be filled in.')
            return
        }
        if(Date.parse(dateBegin)>(Date.parse(dateEnd))) {
            setError('Begin date cannot be after end date.')
            return
        }

        onAdd({name, store, amount: +amount, price: +price, dateBegin, dateEnd});
        handleCancel()
    }

    const handleCancel = () => {
        setName('');
        setStore('');
        setAmount(1);
        setPrice(0);
        setDateBegin('');
        setDateEnd('');
        nameInput.current.focus();
        setError('')
    }

    return (
        <div className="form-wrapper">
            <form className="form-control add-campaign" onSubmit={handleSubmit}>
                <h4>Add campaign</h4>
                <div className="error">{error}</div>
                <div>
                <label>Name</label>
                    <input ref={nameInput} type="text" value={name} onChange={(e) => setName(e.target.value)} autoFocus/> 
                </div>
                <div>
                    <label>Store</label>
                    <input type="text" value={store} onChange={(e) => setStore(e.target.value)}/> 
                </div>
                <div>
                    <label> Amount</label>
                    <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)}/>
                </div>
                <div>
                    <label>Price</label>
                    <input type="number" value={price} onChange={(e) => setPrice(e.target.value)}/> 
                </div>
                <div>
                    <label>Date from</label>
                    <input type="date" value={dateBegin} onChange={(e) => setDateBegin(e.target.value)}/>
                        
                </div> 
                <div>
                    <label>Date to</label>
                    <input type="date" value={dateEnd} onChange={(e) => setDateEnd(e.target.value)}/>
                </div>
                <div>        
                    <input className="btn input-save" type="submit" onSubmit={handleSubmit} value="Save"/>
                    <input className="btn input-cancel" type="reset" onClick={handleCancel} value="Clear"/>
                </div>
            </form>
        </div>
    )
}

export default AddCampaign
