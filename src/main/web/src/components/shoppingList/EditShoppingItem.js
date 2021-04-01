import React, {useState} from 'react'

const EditShoppingItem = ({item, onEdit, setEditClicked}) => {
    const [name, setName] = useState(item.name);

    const handleSubmit = (e) => {
        e.preventDefault();
        onEdit({"shoppingItemId" : item.shoppingItemId, name});
        setName('')
        setEditClicked(false)
    }

    const handleBlur = () => {
        setEditClicked(false)
    }

    return (
        <div className="form-wrapper" tabIndex="-1" onBlur={handleBlur}>
        <form className="form-control edit-list" onSubmit={handleSubmit} >
            <div className="item-input">
                <input type="text" placeholder="New name ..." value={name} onChange={(e) => setName(e.target.value)} autoFocus/>
            </div>
            <input className="btn input-save input-edit-save" type="submit" value="Save" onClick={handleSubmit} />
            <input className="btn input-cancel input-edit-cancel" type="reset" value="Cancel" onClick={handleBlur} />
        </form>
        </div>
    )
}

export default EditShoppingItem