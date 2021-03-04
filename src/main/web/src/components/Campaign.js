import React from 'react';
import {TiDelete} from 'react-icons/ti'
import {MdEdit} from 'react-icons/md'

import '../App.css';

const Campaign = ({campaign, matched, onEdit, onDelete}) => {
  
    return (
            
        <tr className={matched.includes(campaign) ? 'highlight' : ''}>
         
            <td>{campaign.name}</td> 
            <td>{campaign.store}</td>
            <td>{campaign.price}</td>
            <td>{campaign.dateBegin}</td>
            <td>{campaign.dateEnd}</td>
            <td><MdEdit color="grey" size="2rem" onClick={() => onEdit(campaign.id)}/></td>
            <td><TiDelete color="red" size="2rem"  onClick={() => onDelete(campaign.id)}/></td>
    
        </tr>  
    )
}

export default Campaign
