import React, {useState}from 'react';
import {TiDelete} from 'react-icons/ti'
import {MdEdit} from 'react-icons/md'

import EditShoppingItem from './EditShoppingItem'


const ShoppingItem = ({item, onEdit, onDelete, onMatchItem, onCancelMatch}) => {

    const [clicked, setClicked] = useState(false)
    const [editClicked, setEditClicked] = useState(false)

    const handleClick = () => {        
        if(!clicked) {
        onMatchItem(item.name)
        setClicked(true)
        }else{
        onCancelMatch()
        setClicked(false)
        }
    }

    const handleBlur = () => {
        onCancelMatch()
        setClicked(false)
    }

    const handleEdit = () => {
        setEditClicked(true)
    }
 
    return (
        <>
        <div className="shopping-item" tabIndex="-1" onClick={handleClick} onBlur={handleBlur}>
            <span className="item-name">{item.name} </span>  
            <span className="hide">   
                <MdEdit color="grey" size="2rem"  onClick={handleEdit}/>
                <TiDelete color="red" size="2rem" onClick={() => onDelete(item.shoppingItemId)}/>
            </span>
        </div>
        {editClicked && <EditShoppingItem item={item} onEdit={onEdit} setEditClicked={setEditClicked}/>}
        </>
    )
}

export default ShoppingItem
