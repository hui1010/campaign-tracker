import React, {useState} from 'react'

const AddShoppingList = ({onAdd}) => {

    const [name, setName] = useState('');
    const [error, setError] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault();
        if(!name) {
            setError('Item is empty.')
            return
        }
        
        onAdd({name});
        setName('')
        setError('')
    }

    return (
        <div className="form-wrapper">
        <form className="form-control add-list" onSubmit={handleSubmit}>
            <h4>Add shopping item</h4>
            <div className="error">{error}</div>
            <div className="item-input">
                <label>Item</label>
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} autoFocus/>
            </div>
            <div>
                <input className="btn input-save" type="submit" value="Save"/>  
            </div>
        </form>
        </div>
    )
}

export default AddShoppingList
