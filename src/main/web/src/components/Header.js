import React, {useState} from 'react';
import Button from './Button';
import AddCampaign from './AddCampaign';
import AddShoppingList from './AddShoppingItem';

const Header = ({text, showAdd, onAdd}) => {

    return (
        <header>
            <h1>{text}</h1>
            <Button text= {!showAdd ? 'Add' : 'Close'} onClick={onAdd} showAdd={showAdd}></Button>
        </header>
    )
}

export default Header
