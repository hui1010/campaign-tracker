import React, {useState} from 'react'

const AddShoppingList = ({onAdd}) => {

    const [name, setName] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onAdd({name});
        setName('')
    }

    return (
        <div className="form-wrapper">
        <form className="form-control" onSubmit={handleSubmit}>
            <div className="item-input">
                <input type="text" placeholder="Item ..." value={name} onChange={(e) => setName(e.target.value)} autoFocus/>
            </div>
            
               <input className="btn" type="submit" value="Save"/> 
            
        </form>
        </div>
    )
}

export default AddShoppingList
