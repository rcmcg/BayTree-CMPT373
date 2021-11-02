import React, { useEffect } from 'react';
import { getResourcesList } from './Resources.api';

// interface ResourcesProps {
//   Resource: any

// }

// const Resources: React.FC<ResourcesProps> = ({Resource}) => {

// }

function Resources() {
  useEffect(() => {
    const fetchResourceList = async () => {
      const resourceList = await getResourcesList()
      console.log(resourceList)
    }
    fetchResourceList()
  },[])
  return (
    <div className='resources'>
      <h1>Add Resources</h1>
    </div>
  );
}

export default Resources;